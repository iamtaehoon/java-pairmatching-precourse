package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    private List<Crew> crews;

    public Pair(List<Crew> crews) {
        this.crews = crews;
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(crews);
    }

    public boolean hasCrew(Crew crew) {
        return crews.contains(crew);
    }

    public List<Crew> getHisPair(Crew crew) {
        List<Crew> temp = new ArrayList<>();
        for (Crew c : crews) {
            if (c.equals(crew)) {
                continue;
            }
            temp.add(c);
        }
        return temp;
    }
}
