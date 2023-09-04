package com.service;

import com.model.Supply;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralService {
    public static <E> List<E> getRandomItems(List<E> list, int num) {
        List<E> temp = new ArrayList<>(list);
        List<E> rs = new ArrayList<>();
        int idx;
        while (temp.size() > 0 && rs.size() < num) {
            idx = (int) Math.floor(Math.random() * temp.size());
            rs.add(temp.get(idx));
            temp.remove(idx);
        }
        return rs;
    }

    public static String toStringOfSupplies(List<Supply> supplies) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < supplies.size(); i++) {
            res.append(supplies.get(i).getNameSupply());
            if (i != supplies.size() - 1)
                res.append(", ");
        }
        return res.toString();
    }
}
