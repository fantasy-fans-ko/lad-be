package com.fantasy.ladbe.service

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.services.s3.transfer.MultipleFileUpload
import com.amazonaws.services.s3.transfer.TransferManager
import com.fantasy.ladbe.common.log.logger
import com.fantasy.ladbe.handler.exception.BusinessException
import com.fantasy.ladbe.handler.exception.Exceptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
class UploadService(
    @Value("\${cloud.aws.s3.bucket}") val bucketName: String,
    val s3DirectoryClient: TransferManager,
) {
    fun uploadDirectory(s3Directory: String) {
        val localPath: String = when (s3Directory) {
            "players" -> "./src/main/resources/playerImages"
            "htmls" -> "./src/main/resources/htmlResources"
            else -> throw BusinessException(Exceptions.INVALID_UPLOAD_PATH)
        }
        val localDirectory = File(localPath)
        val s3UploadPath = "/upload/$s3Directory/"


        try {
            val uploadDirectory: MultipleFileUpload =
                s3DirectoryClient.uploadDirectory(bucketName, s3UploadPath, localDirectory, true)
            uploadDirectory.waitForCompletion()
            logger().info("[Uploader] Upload Directory to S3 Success !")
        } catch (e: AmazonServiceException) {
//            throw BusinessException(Exceptions.???)
            logger().error("Amazon service error: {}, {}", e.message, e)
        } catch (e: AmazonClientException) {
            logger().error("Amazon client error: {}, {}", e.message, e)
        } catch (e: InterruptedException) {
            logger().error("Transfer interrupted: {}, {}", e.message, e)
        }
        s3DirectoryClient.shutdownNow(false)
    }
}

