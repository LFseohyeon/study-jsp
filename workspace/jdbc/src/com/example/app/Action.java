package com.example.app;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action { //액션 인터페이스
   public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException;
}

