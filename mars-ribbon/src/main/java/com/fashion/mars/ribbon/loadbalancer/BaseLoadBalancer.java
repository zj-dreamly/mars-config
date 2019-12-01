package com.fashion.mars.ribbon.loadbalancer;

import com.fashion.mars.ribbon.ping.IPing;
import com.fashion.mars.ribbon.ping.PingUrl;
import com.fashion.mars.ribbon.rule.IRule;
import com.fashion.mars.ribbon.rule.RoundRobinRule;
import com.fashion.mars.ribbon.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class BaseLoadBalancer  implements ILoadBalancer {

    protected volatile List<Server> allServerList = Collections.synchronizedList(new ArrayList<Server>());

    protected ReadWriteLock allServerLock = new ReentrantReadWriteLock();

    protected IRule rule = new RoundRobinRule();

    protected IPing ping = new PingUrl();


    @Override
    public void addServers(List<Server> newServers) {
        if (CollectionUtils.isNotEmpty(newServers)){
            ArrayList<Server> newList = new ArrayList<Server>();
            newList.addAll(allServerList);
            newList.addAll(newServers);
            setServersList(newList);
        }
    }


    @Override
    public List<Server> getAllServers() {
        return allServerList;
    }

    @Override
    public Server chooseServer() {
        return rule.choose(this);
    }

    @Override
    public void setRule(IRule rule) {
        if (rule!=null){
            this.rule = rule;
        }
    }

    @Override
    public IRule getRule() {
        return this.rule;
    }

    @Override
    public void setPing(IPing ping) {
        if (ping!=null){
            this.ping = ping;
        }
    }

    @Override
    public IPing getPing() {
        return this.ping;
    }


    /**
     * Set the list of servers used as the server pool. This overrides existing
     * server list.
     */
    public void setServersList(List<Server> lsrv) {
        Lock writeLock = allServerLock.writeLock();

        writeLock.lock();
        try {
            ArrayList<Server> allServers = new ArrayList<Server>();
            for (Server server : lsrv) {
                if (server == null) {
                    continue;
                }
                allServers.add(server);
            }

            // This will reset readyToServe flag to true on all servers
            // regardless whether
            // previous priming connections are success or not
            allServerList = allServers;
        } finally {
            writeLock.unlock();
        }
    }

}
