package com.dartflame.test_task.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Gif {
    Map<String,Object> data = new HashMap<>();
    Map<String,Object> meta = new HashMap<>();
}
