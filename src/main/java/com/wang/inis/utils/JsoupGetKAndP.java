package com.wang.inis.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class JsoupGetKAndP {
    private static final String url = "https://burghquayregistrationoffice.inis.gov.ie/Website/AMSREG/AMSRegWeb.nsf/AppSelect?OpenForm";

    public final String[] getDAndP(){

            String[] KV ={};
        try{
               Document document = Jsoup.connect(url).validateTLSCertificates(false).get();

                Element elementK = document.getElementById("k");
                String valueK = elementK.attr("value");
                Element elementP = document.getElementById("p");
                String valueP = elementP.attr("value");
                KV = new String[]{valueK, valueP};


            } catch (IOException e) {
                log.error("Jsoup k&v IO错误");
                e.printStackTrace();
            }
            return KV;
    }


}
