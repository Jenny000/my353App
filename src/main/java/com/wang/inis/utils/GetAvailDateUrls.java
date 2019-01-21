package com.wang.inis.utils;

import com.wang.inis.entity.SlotsCatAndType;
import com.wang.inis.slotJpa.entity.Slots;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;

@Component

@Slf4j
public class GetAvailDateUrls {

    @Autowired
    private JsoupGetKAndP getKAndP;
    @Autowired
    private SlotsCatAndType catAndType;
    @Autowired
    private Slots slots;


    public ArrayList<Slots> getDateUrls() {
        String[] KP = getKAndP.getDAndP();
        ArrayList<Slots> dateUrlList = new ArrayList<>();


        //get urls
        String[] slotsCat = catAndType.getCat();
        String[] slotsType = catAndType.getType();

        for (int i = 0; i < slotsCat.length; i++) {
            for (int j = 0; j < slotsType.length; j++) {

                String urlOfDate = "https://burghquayregistrationoffice.inis.gov.ie/Website/AMSREG/AMSRegWeb.nsf/(getApps4DTAvailability)?" +
                        "readform&&cat=" + slotsCat[i] + "&sbcat=All&typ=" + slotsType[j] + "&k=" + KP[0]
                        + "&p=" + KP[1] + "&_="
                        + (new Date().getTime());

                slots.setCat(slotsCat[i]);
                slots.setType(slotsType[j]);
                slots.setUrl(urlOfDate);
                slots.setK(KP[0]);
                slots.setP(KP[1]);

                dateUrlList.add(new Slots(slots.getCat(), slots.getType(), slots.getUrl(), slots.getK(), slots.getP(), slots.getcreateTime()));
            }
        }

        return dateUrlList;

    }
}
