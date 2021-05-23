package com.marshmallow.hiring.domain;

import lombok.Value;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

@Value
public class OilCleaner {

    Set<Vector> oilPatchLocations;
    int oilPatchesCleaned;

    public OilCleaner(Set<Vector> oilPatchLocations) {
        this(oilPatchLocations, 0);
    }

    private OilCleaner(Set<Vector> oilPatchLocations, int oilPatchesCleaned) {
        requireNonNull(oilPatchLocations, "oilPatchLocations must not be null");

        this.oilPatchLocations = unmodifiableSet(oilPatchLocations);
        this.oilPatchesCleaned = oilPatchesCleaned;
    }

    public OilCleaner cleanLocation(Vector location) {
        requireNonNull(location, "location must not be null");

        if (oilPatchLocations.contains(location)) {
            Set<Vector> updatedLocations = new HashSet<>(oilPatchLocations);
            updatedLocations.remove(location);
            return new OilCleaner(updatedLocations, oilPatchesCleaned + 1);
        }

        return this;
    }

}
