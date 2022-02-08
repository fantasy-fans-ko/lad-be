package com.fantasy.ladbe.dto

class UploadDto {
    class Request {
        data class UploadS3Path(val s3Path: String)
    }
}
