package com.marshmallow.hiring.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static com.marshmallow.hiring.domain.Vector.*;
import static org.assertj.core.api.Assertions.assertThat;

class OilCleanerTest {

    private static final Vector OIL_PATCH_1 = vector(1, 1);
    private static final Vector OIL_PATCH_2 = vector(2, 2);
    private static final Vector OIL_PATCH_3 = vector(3, 3);
    private static final Set<Vector> OIL_PATCHES = Set.of(OIL_PATCH_1, OIL_PATCH_2, OIL_PATCH_3);

    @Test
    public void newOilCleaner_initialized() {
        OilCleaner cleaner = new OilCleaner(Collections.emptySet());

        assertThat(cleaner.getOilPatchLocations()).isEmpty();
        assertThat(cleaner.getOilPatchesCleaned()).isEqualTo(0);
    }

    @Test
    public void cleanLocation_noOilPatch() {
        OilCleaner cleaner = new OilCleaner(OIL_PATCHES).cleanLocation(vector(0, 0));

        assertThat(cleaner.getOilPatchLocations()).isEqualTo(OIL_PATCHES);
        assertThat(cleaner.getOilPatchesCleaned()).isEqualTo(0);
    }

    @Test
    public void cleanLocation_oilPatchPresent_counterIncremented() {
        OilCleaner cleaner = new OilCleaner(OIL_PATCHES).cleanLocation(OIL_PATCH_1);

        assertThat(cleaner.getOilPatchLocations()).isEqualTo(Set.of(OIL_PATCH_2, OIL_PATCH_3));
        assertThat(cleaner.getOilPatchesCleaned()).isEqualTo(1);
    }

    @Test
    public void cleanTwoDifferentLocations_counterIncrementedTwice() {
        OilCleaner cleaner = new OilCleaner(OIL_PATCHES)
                .cleanLocation(OIL_PATCH_1)
                .cleanLocation(OIL_PATCH_3);

        assertThat(cleaner.getOilPatchLocations()).isEqualTo(Set.of(OIL_PATCH_2));
        assertThat(cleaner.getOilPatchesCleaned()).isEqualTo(2);
    }

    @Test
    public void cleanSameLocationTwice_counterOnlyIncrementedOnce() {
        OilCleaner cleaner = new OilCleaner(OIL_PATCHES)
                .cleanLocation(OIL_PATCH_1)
                .cleanLocation(OIL_PATCH_1);

        assertThat(cleaner.getOilPatchLocations()).isEqualTo(Set.of(OIL_PATCH_2, OIL_PATCH_3));
        assertThat(cleaner.getOilPatchesCleaned()).isEqualTo(1);
    }

}
