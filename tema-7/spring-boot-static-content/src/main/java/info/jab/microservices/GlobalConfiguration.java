package info.jab.microservices;

import lombok.Data;

import java.util.List;

@Data
public class GlobalConfiguration {

    private List<Host> hosts;

    @Data
    public static class Host {
        private String address;
        private String name;
    }
}