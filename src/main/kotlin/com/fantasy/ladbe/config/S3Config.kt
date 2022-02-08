package com.fantasy.ladbe.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {
    @Bean
    fun amazonS3Client(
        @Value("\${cloud.aws.credentials.access-key}") accessKey: String,
        @Value("\${cloud.aws.credentials.secret-key}") secretKey: String,
        @Value("\${cloud.aws.region.static}") region: String,
    ): TransferManager {
        val awsCredentials = BasicAWSCredentials(accessKey, secretKey)

        val s3Client: AmazonS3 =
            AmazonS3ClientBuilder.standard().withCredentials(AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region).build()

        return TransferManagerBuilder.standard().withS3Client(s3Client).build()
    }
}
