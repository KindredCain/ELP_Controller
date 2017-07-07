package com.elp.service;

import com.elp.enums.PathEnum;
import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.repository.PagerankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Created by NWJ on 2017/7/4.
 */

@Service
public class PagerankService {
    @Autowired
    private PagerankDao pagerankDao;

    Map<String, Double> rank;
    Map<String, Double> rankItem;
    Map<String, List<String>> m;
    List<Map.Entry<String, Double>> result;

    /*主程序*/
    public List<Map.Entry<String, Double>> mainRank (String key){
        this.loadMap(key);
        for(int i = 0; i < 15; i++) {
            this.getRank(key);
        }
        this.deleteSame(key);
        this.sortRank();
        return result;
    }

    /*计算rank*/
    public void getRank (String key){
        rankItem = new HashMap<String, Double>();

        for(Map.Entry<String, Double> entry : rank.entrySet()){
            List<String> list = m.get(entry.getKey());
            Double value = entry.getValue()*1.0/list.size();
            for(int i = 0; i < list.size(); i++){
                this.addRank(list.get(i), value);
            }
        }
        rankItem.put(key, rankItem.get(key) + 0.2);
        rank = rankItem;
    }

    /*累加rank*/
    public void addRank (String key, Double value){
        value = value * 0.8;
        if(rankItem.containsKey(key)){
            rankItem.put(key, value + rankItem.get(key));
        } else {
            rankItem.put(key, value);
        }
    }

    /*获取联系*/
    public void getMap(){
        List<String> listItem;
        List<Object[]> list = pagerankDao.findPagerank();
        for(int i = 0; i < list.size(); i++){
            Object[] item = list.get(i);
            String keyA = (String) item[0];
            String keyB = (String) item[1];
            if (m.containsKey(keyA)){
                listItem = m.get(keyA);
                listItem.add(keyB);
            } else {
                listItem = new ArrayList<String>();
                listItem.add(keyB);
            }
            m.put(keyA, listItem);
        }
        for (Map.Entry<String, List<String>> entry : m.entrySet()) {
            rank.put(entry.getKey(), 1.0 / list.size());
        }
    }

    /*获取矩阵*/
    public void loadMap(String key){
        Integer time = 0;
        Integer num = 0;
        File file=new File(PathEnum.MAP_PATH.getPath());

        m = new HashMap<String, List<String>>();
        rank = new HashMap<String, Double>();

        if(file.isFile() && file.exists()){
            String lineTxt = null;
            try {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                if((lineTxt = bufferedReader.readLine()) != null) {
                    time = Integer.valueOf(lineTxt);
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        num++;
                        String uuid = lineTxt.substring(0, lineTxt.indexOf(";"));
                        lineTxt = lineTxt.substring(lineTxt.indexOf(";") + 1);
                        List<String> list = new ArrayList<String>();
                        while (lineTxt.indexOf(",") > 0) {
                            list.add(lineTxt.substring(0, lineTxt.indexOf(",")));
                            lineTxt = lineTxt.substring(lineTxt.indexOf(",") + 1);
                        }
                        list.add(lineTxt);
                        m.put(uuid, list);
                    }
                    for (Map.Entry<String, List<String>> entry : m.entrySet()) {
                        rank.put(entry.getKey(), 1.0 / num);
                    }
                } else {
                    getMap();
                    Calendar now = Calendar.getInstance();
                    time = now.get(Calendar.YEAR) * 10000
                            + (now.get(Calendar.MONTH) + 1) * 100
                            + now.get(Calendar.DAY_OF_MONTH);
                    saveMap();
                }
                bufferedReader.close();
                read.close();
            } catch (Exception e){
                throw new MyException(ResultEnum.ERROR_104);
            }
        } else {
            getMap();
            Calendar now = Calendar.getInstance();
            time = now.get(Calendar.YEAR) * 10000
                    + (now.get(Calendar.MONTH) + 1) * 100
                    + now.get(Calendar.DAY_OF_MONTH);
            saveMap();
        }
        if(!this.isOkMap(key, time)){
            getMap();
            saveMap();
        }
    }

    /*判断矩阵*/
    public Boolean isOkMap (String key, Integer day){
        Calendar now = Calendar.getInstance();
        Integer time = now.get(Calendar.YEAR) * 10000
                + (now.get(Calendar.MONTH) + 1) * 100
                + now.get(Calendar.DAY_OF_MONTH);
        if(!m.containsKey(key)){
            return false;
        } else if(!time.equals(day)) {
            return false;
        } else {
            return true;
        }
    }

    /*存储矩阵*/
    public void saveMap (){
        Calendar now = Calendar.getInstance();
        Integer time = now.get(Calendar.YEAR) * 10000
                + (now.get(Calendar.MONTH) + 1) * 100
                + now.get(Calendar.DAY_OF_MONTH);
        String saveItem;
        int i;
        File mapsave = new File(PathEnum.MAP_PATH.getPath());

        if (mapsave.exists()){
            mapsave.delete();
        }
        try {
            mapsave.createNewFile();
            FileWriter resultFile = new FileWriter(mapsave);
            PrintWriter mapPrint = new PrintWriter(resultFile);
            mapPrint.println(time);
            for(Map.Entry<String, List<String>> entry : m.entrySet()){
                saveItem = entry.getKey() + ";";
                List<String> list = entry.getValue();
                for(i = 0; i < list.size() - 1; i++){
                    saveItem += list.get(i) + ",";
                }
                saveItem += list.get(i);
                mapPrint.println(saveItem);
            }
            mapPrint.close();
            resultFile.close();
        } catch (Exception e){
            throw new MyException(ResultEnum.ERROR_103);
        }
    }

    /*删除重复关系*/
    public void deleteSame(String key){
        List<String> list = m.get(key);
        for(int i = 0; i < list.size(); i++){
            rank.remove(list.get(i));
        }
    }

    /*rank排序*/
    public void sortRank(){
        result = new ArrayList<Map.Entry<String,Double>>(rank.entrySet());
        Collections.sort(result,new Comparator<Map.Entry<String, Double>>() {

            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if(o2.getValue() > o1.getValue())
                    return 1;
                else if (o2.getValue() == o1.getValue())
                    return 0;
                else
                    return -1;
            }
        });
    }
}
