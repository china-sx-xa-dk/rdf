package com.sxgokit.rdf.config.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dante
 * @package com.virgin.common.component
 * @date 2018/10/24 002411:11
 */
@Component
@Slf4j
public class ListUtilComponent {


    public <T> List<T> randomElement(List<T> list, int maxMiss) {

        if (null == list || list.isEmpty() || maxMiss >= list.size()) {
            return list;
        }

        int miss = (int) (Math.random() * maxMiss) + 1;//最多miss掉几个
        List<T> result = new ArrayList<>();
        Collections.shuffle(list);
        Collections.shuffle(list);
        result = list.subList(0, list.size() - miss);
        return result;
    }

    public <T> List<T> randomElement(int maxGet, List<T> list) {
        if (null == list || list.isEmpty() || list.size() < 3) {
            return new ArrayList<>();
        }
        int element = (int) (Math.random() * maxGet) + 1;//最多得到几个
        List<T> result = new ArrayList<>();
        Collections.shuffle(list);
        Collections.shuffle(list);
        result = list.subList(0, element);
        return result;


    }


    public int random0_2() {
        return (int) (Math.random() * 2);
    }


}
