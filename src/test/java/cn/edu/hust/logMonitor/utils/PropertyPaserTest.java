package cn.edu.hust.logMonitor.utils;


import org.junit.Test;

public class PropertyPaserTest {
    @Test
    public void getZKHost()
    {
        String hosts=ZkConfigPropertyParser.getZkHosts();
        System.out.println(hosts);
    }


    @Test
    public void getKafKaTopic()
    {
        String topic=ZkConfigPropertyParser.getKafKaTopic();
        System.out.println(topic);
    }

    @Test
    public void getZkPath_ID()
    {
        String path=ZkConfigPropertyParser.getZkPath_ID();
        System.out.println(path);
    }

    @Test
    public void getZKRoot()
    {
        String root=ZkConfigPropertyParser.getZKRoot();
        System.out.println(root);
    }
}
