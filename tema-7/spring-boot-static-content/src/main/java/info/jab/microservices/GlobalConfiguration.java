package info.jab.microservices;

import java.util.List;
import java.util.Objects;

public class GlobalConfiguration {

    private List<Host> hosts;

    public GlobalConfiguration(List<Host> hosts) {
        this.hosts = hosts;
    }

    public GlobalConfiguration() {
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GlobalConfiguration that = (GlobalConfiguration) o;
        return Objects.equals(hosts, that.hosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hosts);
    }
}
