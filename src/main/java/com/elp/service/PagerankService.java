package com.elp.service;

import com.elp.enums.PathEnum;
import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;

import java.io.*;
import java.util.*;

/**
 * Created by NWJ on 2017/7/4.
 */
public class PagerankService {

    Map<String, Double> rank;
    Map<String, Double> rankItem;
    Map<String, List<String>> m;

    /*主程序*/
    public Map<String, Double> mainRank (String key){
        this.loadMap(key);
        this.getRank(key);
        return rank;
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
                time = Integer.valueOf(bufferedReader.readLine());
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    num++;
                    String uuid = lineTxt.substring(0, lineTxt.indexOf(";"));
                    lineTxt.substring(lineTxt.indexOf(";") + 1);
                    List<String> list = new ArrayList<String>();
                    while(lineTxt.indexOf(",") > 0){
                        list.add(lineTxt.substring(0, lineTxt.indexOf(",")));
                        lineTxt.substring(lineTxt.indexOf(",") + 1);
                    }
                    list.add(lineTxt);
                    m.put(uuid, list);
                }
                for(Map.Entry<String, List<String>> entry : m.entrySet()){
                    rank.put(entry.getKey(), 1.0/num);
                }
            } catch (Exception e){
                throw new MyException(ResultEnum.ERROR_104);
            }
        }
        if(!this.isOkMap(key, time)){
            /*获取联系*/
            saveMap();
        }
    }

    /*判断矩阵*/
    public Boolean isOkMap (String key, Integer day){
        Calendar now = Calendar.getInstance();
        Integer time = now.get(Calendar.YEAR) * 10000
                + (now.get(Calendar.MONTH) + 1) * 100
                + now.get(Calendar.DAY_OF_MONTH);
        if(!rankItem.containsKey(key)){
            return false;
        } else if(time != day) {
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
}
