package com.dartflame.test_task.dao;

import com.dartflame.test_task.entity.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${app.feign.config.name3}", url = "${app.feign.config.url3}")
public interface GiphyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/random")
    Gif getGif(@RequestParam (required = true, defaultValue = "")String api_key,
                   @RequestParam(required = false, defaultValue = "") String tag,
                   @RequestParam (required = false, defaultValue = "") String rating,
                   @RequestParam (required = false, defaultValue = "") String random_id);

}