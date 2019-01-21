package com.wang.inis.utils;

import com.wang.inis.slotJpa.entity.SlotSet;
import com.wang.inis.slotJpa.entity.Slots;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class GetAvailSlots {

    @Autowired
    private GetAvailDateUrls dateUrls;
    @Autowired
    private Slots slots;
    @Autowired
    private SlotSet slSet;
    private Set<Slots> slotSet = new HashSet<>();


    public Set<Slots> getAvailSlot() {
        Set<Slots> slotSet = new HashSet<>();

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            service.submit(new Runnable() {


                @Override
                public void run() {


                    log.info(Thread.currentThread().getName() + " ====is running");


                    String url;
                    for (Slots s : dateUrls.getDateUrls()) {
                        log.info("slots ={}", s.toString());
                        url = s.getUrl();

                        try {
                            JSONObject datesObj = new JSONObject(HttpGetUtils.responseEntity(url, 5000));

                            if (datesObj.toString().contains("[]")) {
                                log.info(s.getCat() + "      " + s.getType() + "  no slots");

                            } else {
                                JSONArray datesArray = datesObj.getJSONArray("slots");
                                if (datesArray.length() > 0) {
                                    datesArray = datesObj.getJSONArray("slots");

                                    for (int i = 0; i < datesArray.length(); i++) {
                                        s.setDate(datesArray.get(i).toString());
                                        String slotUrl = "https://burghquayregistrationoffice.inis.gov.ie/Website/AMSREG/AMSRegWeb.nsf/(getApps4DT)?" +
                                                "readform&dt=" + s.getDate() + "&cat=" + s.getCat() + "&sbcat=All&typ="
                                                + s.getType() + "&k=" + slots.getK() + "&p=" + slots.getP() + "&_="
                                                + (new Date().getTime());

                                        JSONObject slotObj = new JSONObject(HttpGetUtils.responseEntity(slotUrl, 5000));


                                        if (slotObj.toString().contains("[]") || slotObj.toString().contains("empty")) {
                                            log.info(s.getCat() + "      " + s.getType() + "  no slots");

                                        } else {
                                            for (int j = 0; j < slotObj.getJSONArray("slots").length(); j++) {
                                                JSONObject createTime = slotObj.getJSONArray("slots").getJSONObject(j);
                                                s.setTime(createTime.getString("time"));
                                                s.setId(createTime.getString("id"));
                                                s.setcreateTime(new Date());

                                                //s = new Slots("aaa","bbb","ccc");
                                                slotSet.add(new Slots(s.getCat(), s.getType(), s.getDate(), s.getTime(), s.getId(), s.getK(), s.getP(),s.getcreateTime()));
                                                log.info("slots={}",s.toString());
                                                log.info(Thread.currentThread().getName() +"size  " + slotSet.size());

                                            }

                                        }
                                    }

                                } else {
                                    log.error("JSON DATA IS EMPTY");

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            log.error("JSON DATA Error");
                        }
                    }

                }
            });

        }

        service.shutdown();

            try {
                // awaitTermination返回false即超时会继续循环，返回true即线程池中的线程执行完成主线程跳出循环往下执行，每隔2秒循环一次
                while (!service.awaitTermination(2, TimeUnit.SECONDS));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        log.info("set total size " + slotSet.size());
        return slotSet;

    }
}






/**

    public  Set<Slots> getAvailSlots() {

        //List<Slots> sList = new ArrayList<>();
        Set<Slots> slotSet = new HashSet<>();


        String url;

        for(Slots s : dateUrls.getDateUrls()){
            log.info("slots ={}",s.toString());
            url = s.getUrl();

            try {
                JSONObject datesObj = new JSONObject(HttpGetUtils.responseEntity(url,5000));
                if(datesObj.toString().contains("[]")){
                    log.info(s.getCat() + "      " + s.getType() + "  no slots");

                }else {
                    JSONArray datesArray = datesObj.getJSONArray("slots");
                    if(datesArray.length() > 0){
                        datesArray = datesObj.getJSONArray("slots");

                        for(int i=0; i<datesArray.length(); i++){
                            s.setDate(datesArray.get(i).toString());
                            String slotUrl = "https://burghquayregistrationoffice.inis.gov.ie/Website/AMSREG/AMSRegWeb.nsf/(getApps4DT)?" +
                                    "readform&dt=" + s.getDate() + "&cat=" + s.getCat() + "&sbcat=All&typ="
                                    + s.getType() + "&k=" + slots.getK() + "&p=" + slots.getP() + "&_="
                                    + (new Date().getTime());

                            JSONObject slotObj = new JSONObject(HttpGetUtils.responseEntity(slotUrl,5000));


                            if(slotObj.toString().contains("[]") || slotObj.toString().contains("empty")){
                                log.info(s.getCat() + "      " + s.getType() + "  no slots");

                            }else {
                                for (int j = 0; j < slotObj.getJSONArray("slots").length(); j++) {
                                    JSONObject createTime = slotObj.getJSONArray("slots").getJSONObject(j);
                                    s.setTime(createTime.getString("time"));
                                    s.setId(createTime.getString("id"));
                                    s.setcreateTime(new Date());
                                    slotSet.add(new Slots(s.getCat(), s.getType(), s.getDate(), s.getTime(), s.getId(), s.getK(), s.getP(),s.getcreateTime()));
                                }

                            }
                        }

                    }else {
                        log.error("JSON DATA IS EMPTY");
                        return null;
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
                log.error("JSON DATA Error");
            }
        }

        return slotSet;
    }

**/



