package org.example.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {

    private final List<Rule> rules;

    public RuleEngine() {
        this.rules = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void evaluate() {
        for(Rule rule : rules) {
            if(rule.isMet()) {
                rule.execute();
            }
        }
    }

}
