package com.AllException;
// 리그오브레전드 검색 시 자주 발생되는 429에러 처리를 위한 런타임
// 429에러란 ? 빠른 주기로 요청을 하여서 난 오류 또는 요청횟수의 제한.
public class Response429Exception extends RuntimeException {
}
