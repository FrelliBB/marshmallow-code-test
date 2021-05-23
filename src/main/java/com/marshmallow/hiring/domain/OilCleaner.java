package com.marshmallow.hiring.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Getter
@ToString
public class OilCleaner {

    private final Set<Vector> oilPatchLocations;
    private final int oilPatchesCleaned;

    OilCleaner(Set<Vector> oilPatchLocations) {
        this(oilPatchLocations, 0);
    }

    private OilCleaner(Set<Vector> oilPatchLocations, int oilPatchesCleaned) {
        this.oilPatchLocations = unmodifiableSet(oilPatchLocations);
        this.oilPatchesCleaned = oilPatchesCleaned;
    }

    public OilCleaner cleanLocation(Vector location) {
        if (oilPatchLocations.contains(location)) {
            Set<Vector> updatedLocations = new HashSet<>(oilPatchLocations);
            updatedLocations.remove(location);
            return new OilCleaner(updatedLocations, oilPatchesCleaned + 1);
        }

        return this;
    }

}
