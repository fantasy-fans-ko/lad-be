package com.fantasy.ladbe.controller

import com.fantasy.ladbe.service.AcquisitionService
import org.springframework.web.bind.annotation.RestController

@RestController
class AcquisitionController(
    val acquisitionService: AcquisitionService,
)
