package com.mooney.gateway.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT, reason = "call to backend service timed out")
public class BackendServerTimedOutCall extends RuntimeException {}
