package com.sunlights.common.vo;

import com.fasterxml.jackson.databind.JsonNode;
import play.i18n.Messages;
import play.libs.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuan on 9/22/14.
 */
public class MessageVo<V> {

    private Message message;
    private V value;

    public MessageVo(Message message) {
        this.message = message;
    }

    public void setValue(V value){
        this.value = value;
    }

    public V getValue(){
        return this.value;
    }

    public JsonNode toJson(){
        return Json.toJson(this);
    }

}