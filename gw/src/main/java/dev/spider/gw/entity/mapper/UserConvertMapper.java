package dev.spider.gw.entity.mapper;

import dev.spider.gw.entity.dto.UserDTO;
import dev.spider.gw.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author spider
 */
@Mapper
public interface UserConvertMapper {

    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    @Mapping(source = "name", target = "userName")
    @Mapping(source = "telephone", target = "phone")
    @Mapping(source = "gitHub",target="netAddress")
    User convert(UserDTO userDTO);

    static void main(String[] args) {
        UserDTO userDTO = new UserDTO();
        userDTO.setAddress("a");
        userDTO.setName("a");
        userDTO.setEmail("a");
        userDTO.setTelephone("a");
        User convert = UserConvertMapper.INSTANCE.convert(userDTO);
        System.out.println(convert.toString());
    }
}
