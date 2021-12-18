package info.jab.microservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataTypesController {

    @GetMapping(value = "/dt/map")
    public @ResponseBody Map<String, String> getMap() {
        Map<String,String> myMap = new HashMap<String, String>();
        myMap.put("a", "1");
        myMap.put("b", "2");
        return myMap;
    }

    @GetMapping(value = "/dt/list")
    public @ResponseBody List<String> getList() {
        return List.of(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5");
    }

    @GetMapping(value = "/dt/object")
    public @ResponseBody MyResponse getObject() {
        return new MyResponse("OK");
    }

    @GetMapping(value = "/dt/enum")
    public @ResponseBody List<MyEnum> getListFromEnum() {
        List<MyEnum> enumList = Arrays.asList(MyEnum.values());
        return enumList;
    }
}
