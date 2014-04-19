/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.app;

import java.util.HashMap;
import java.util.Map;

public class Activities {

    public static final String[] activitiesApiStrings = {
            "other",
            "walk",
            "run",
            "weights",
            "cross_train",
            "bike",
            "stationary",
            "elliptical",
            "cardio",
            "yoga",
            "pilates",
            "dance",
            "tennis",
            "soccer",
            "basketball",
            "swim",
            "hike",
            "ski",
            "only_steps"
    };

    public static final Map<String, Integer> activitiesNameIntMap;
    static
    {
        activitiesNameIntMap = new HashMap<String, Integer>();
        activitiesNameIntMap.put("other", R.string.activ_other);
        activitiesNameIntMap.put("walk", R.string.activ_walk);
        activitiesNameIntMap.put("run", R.string.activ_run);
        activitiesNameIntMap.put("weights", R.string.activ_weights);
        activitiesNameIntMap.put("cross_train", R.string.activ_cross_train);
        activitiesNameIntMap.put("bike", R.string.activ_bike);
        activitiesNameIntMap.put("stationary", R.string.activ_stationary);
        activitiesNameIntMap.put("elliptical", R.string.activ_elliptical);
        activitiesNameIntMap.put("cardio", R.string.activ_cardio);
        activitiesNameIntMap.put("yoga", R.string.activ_yoga);
        activitiesNameIntMap.put("pilates", R.string.activ_pilates);
        activitiesNameIntMap.put("dance", R.string.activ_dance);
        activitiesNameIntMap.put("tennis", R.string.activ_tennis);
        activitiesNameIntMap.put("soccer", R.string.activ_soccer);
        activitiesNameIntMap.put("basketball", R.string.activ_basketball);
        activitiesNameIntMap.put("swim", R.string.activ_swim);
        activitiesNameIntMap.put("hike", R.string.activ_hike);
        activitiesNameIntMap.put("ski", R.string.activ_ski);
        activitiesNameIntMap.put("only_steps", R.string.activ_only_steps);
    }
    public static final Map<String, Integer> activitiesIconIntMap;
    static
    {
        activitiesIconIntMap = new HashMap<String, Integer>();
        activitiesIconIntMap.put("other", R.drawable.activity_icon_other);
        activitiesIconIntMap.put("walk", R.drawable.activity_icon_walk);
        activitiesIconIntMap.put("run", R.drawable.activity_icon_run);
        activitiesIconIntMap.put("weights", R.drawable.activity_icon_weights);
        activitiesIconIntMap.put("cross_train", R.drawable.activity_icon_cross_train);
        activitiesIconIntMap.put("bike", R.drawable.activity_icon_bike);
        activitiesIconIntMap.put("stationary", R.drawable.activity_icon_stationary);
        activitiesIconIntMap.put("elliptical", R.drawable.activity_icon_elliptical);
        activitiesIconIntMap.put("cardio", R.drawable.activity_icon_cardio);
        activitiesIconIntMap.put("yoga",  R.drawable.activity_icon_yoga);
        activitiesIconIntMap.put("pilates", R.drawable.activity_icon_pilates);
        activitiesIconIntMap.put("dance", R.drawable.activity_icon_dance);
        activitiesIconIntMap.put("tennis", R.drawable.activity_icon_tennis);
        activitiesIconIntMap.put("soccer", R.drawable.activity_icon_soccer);
        activitiesIconIntMap.put("basketball", R.drawable.activity_icon_basketball);
        activitiesIconIntMap.put("swim", R.drawable.activity_icon_swim);
        activitiesIconIntMap.put("hike", R.drawable.activity_icon_hike);
        activitiesIconIntMap.put("ski", R.drawable.activity_icon_ski);
        activitiesIconIntMap.put("only_steps", R.drawable.activity_icon_only_steps);
    }

}
