package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pairs {
    private List<Pair> pairs;

    public Pairs(List<Crew> crews) {
        this.pairs = makePairs(crews);
    }

    private List<Pair> makePairs(List<Crew> crews) {
        List<Pair> temp = new ArrayList<>();
        for (int i = 0; i < crews.size()/2-1; i++) {
            temp.add(new Pair(Arrays.asList(crews.get(2 * i), crews.get(2 * i + 1))));
        }
        temp.add(makeLastPair(crews));
        return temp;
    }

    private Pair makeLastPair(List<Crew> crews) {
        if (crews.size() % 2 == 0) {
            return makeLastPairIfCrewsAreEven(crews);
        }
        return makeLastPairIfCrewsAreOdd(crews);
    }

    private Pair makeLastPairIfCrewsAreOdd(List<Crew> crews) {
        return new Pair(
            Arrays.asList(crews.get(crews.size() - 3), crews.get(crews.size() - 2), crews.get(crews.size() - 1)));
    }

    private Pair makeLastPairIfCrewsAreEven(List<Crew> crews) {
        return new Pair(Arrays.asList(crews.get(crews.size() - 2), crews.get(crews.size() - 1)));
    }

    public List<Pair> getPairs() {
        return Collections.unmodifiableList(pairs);
    }

    public boolean duplicatePair(Pairs pairToCompare) {
        for (Pair pair : pairToCompare.getPairs()) {
            if (pairs.contains(pair)) {
                return true;
            }
        }
        return false;
    }
}
