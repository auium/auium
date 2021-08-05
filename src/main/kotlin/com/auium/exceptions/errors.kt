package com.auium.exceptions

class WebDriverAgentServerException : RuntimeException("WebDriverAgent服务异常，请确认服务是否正常并且可被外部访问！")

class CreateSessionException: RuntimeException("创建新的会话失败！！！")