package com.example.taskmanager.exception

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {

        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = 400,
            message = ex.bindingResult.fieldError?.defaultMessage
                ?: "Validation Error"
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraint(ex: ConstraintViolationException): ResponseEntity<ErrorResponse> {

        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = 400,
            message = ex.message ?: "Constraint Violation"
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneral(ex: Exception): ResponseEntity<ErrorResponse> {

        val error = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = 500,
            message = ex.message ?: "Internal Server Error"
        )

        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}