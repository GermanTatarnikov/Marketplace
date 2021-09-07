package com.german.exception;

public interface CheckExceptionMessages {

    String NOT_NULL_ID = "Создаваемая сущность не должна иметь уникальный идентификатор. ";
    String NOT_FOUND_ID = "Не найден уникальной идентификатор. ";
    String NOT_FOUND_PRODUCT = "Не найден товар/услуга. ";
    String NOT_FOUND_ORDER = "Заказ не найден. ";
    String NOT_FOUND_PRODUCT_ARTICLE = "Товара/услуги с таким артиклем не существует";
    String PRODUCT_DELETED = "Данный товар удалён";
    String UNKNOWN_EXCEPTION = "Неизвестная ошибка. ";
}
