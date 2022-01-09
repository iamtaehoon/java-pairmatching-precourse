package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {
    private List<Crew> pair;

    public Pair(List<Crew> crews) {
        this.pair = crews;
    }

    public List<Crew> getPair() {
        return Collections.unmodifiableList(pair);
    }

    public boolean hasCrew(Crew crew) {
        return pair.contains(crew);
    }

    public List<Crew> getHisPair(Crew crew) {
        List<Crew> temp = new ArrayList<>();
        for (Crew c : pair) {
            if (c.equals(crew)) {
                continue;
            }
            temp.add(c);
        }
        return temp;
    }

    @Override
    public String toString() {
        List<String> crewNames = pair.stream().map(crew -> crew.getName()).collect(Collectors.toList());
        return String.join(" : ", crewNames);
    }
}
