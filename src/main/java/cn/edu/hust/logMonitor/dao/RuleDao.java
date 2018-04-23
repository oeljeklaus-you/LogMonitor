package cn.edu.hust.logMonitor.dao;

import cn.edu.hust.logMonitor.bean.Rule;


import java.util.List;

public interface RuleDao {
    List<Rule> getAllRules();
}
