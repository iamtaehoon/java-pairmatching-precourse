package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    // TODO : 내가 직접 equals, hashCode를 만들 줄 알아야겠다. 자동으로 만들어준 값으로는 할 수 없음. 지금 만들어진게 맞는지도 확신이 안듬.
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pair pair1 = (Pair)o;
        return getPair().containsAll(pair1.getPair()) && pair1.getPair().containsAll(getPair());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getPair());
    }

    @Override
    public String toString() {
        List<String> crewNames = pair.stream().map(crew -> crew.getName()).collect(Collectors.toList());
        return String.join(" : ", crewNames);
    }
}
