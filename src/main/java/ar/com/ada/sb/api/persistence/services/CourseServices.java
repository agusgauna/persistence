package ar.com.ada.sb.api.persistence.services;

import ar.com.ada.sb.api.persistence.model.dto.CourseDto;
import ar.com.ada.sb.api.persistence.model.entity.Course;
import ar.com.ada.sb.api.persistence.model.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseServices")
public class CourseServices implements Services<CourseDto>{

    private final CourseMapper courseMapper;

    public CourseServices(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDto save(CourseDto dto) {
        // 1)consultar en la base de datos si existe un curso con el codigo que tiene en el dto (findByCode)
        // 2) si existe disparar una excepcion de tipo BusinessLogicException que indique que no se puede guardar en
        // curso con ese codigo porque ya existe uno
        // 3) si no existe, se convierte el dto a entity y se guarde en una variable de ese tipo
        Course course = courseMapper.toEntity(dto);

        // 4) se le indica al repository que guarde esa variable de tipo entity en la base de datos (save)

        // 5) se convierte la variable de tipo entity, guardada en la base de datos, a dto
        CourseDto saveDto = courseMapper.toDto(course);

        // 6) se retorna ese dto convertido desde entity
        return null;
    }

    @Override
    public List<CourseDto> findAll() {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
