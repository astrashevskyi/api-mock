/*
 * Hitachi Confidential
 * All Rights Reserved. Copyright (C) 2023, Hitachi, Ltd.
 */

package org.no.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {
    @GetMapping("/v1")
    public String responseV1() {
        return "real api service response v1";
    }

    @GetMapping("/v2")
    public String responseV2() {
        return "real api service response v2";
    }
}
