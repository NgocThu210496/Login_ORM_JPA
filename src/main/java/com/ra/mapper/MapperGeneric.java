package com.ra.mapper;

public interface MapperGeneric<E,U,V> { //có kiểu dưx liệu đại diện
    //E: entity, U: request, V: response
    //phương thức chuyển đối từ  requestDTO ->Entity
    E mapperRequestToEntity(U u); //(T t):đầu vào là 1 requestDTO, E: và trả về Entity
    //phương thức chuyển từ Entity -> responseDTO
    V mapperEntityToResponse(E e);
}
