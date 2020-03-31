package ar.com.ada.sb.api.persistence.services;

import ar.com.ada.sb.api.persistence.exception.ApiEntityError;
import ar.com.ada.sb.api.persistence.exception.BusinessLogicException;
import ar.com.ada.sb.api.persistence.model.dto.CourseDto;
import ar.com.ada.sb.api.persistence.model.entity.Course;
import ar.com.ada.sb.api.persistence.model.mapper.CourseMapper;
import ar.com.ada.sb.api.persistence.model.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("courseServices")
public class CourseServices implements Services<CourseDto>{

    @Autowired @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public CourseServices(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDto save(CourseDto dto) {
        // 1)consultar en la base de datos si existe un curso con el codigo que tiene en el dto (findByCode)
        Optional<Course> byCode = courseRepository.findByCode(dto.getCode());
        // 2) si existe disparar una excepcion de tipo BusinessLogicException que indique que no se puede guardar en
        // curso con ese codigo porque ya existe uno
        //byCode.isPresent();
        byCode.ifPresent(course -> {
            ApiEntityError apiEntityError = new ApiEntityError(
                    "Course", "AlreadyExists", "The course code" + course.getCode()+ "is already assigned"
            );
            throw new BusinessLogicException("course already exists", HttpStatus.CONFLICT, apiEntityError);
        });
         // 3) si no existe, se convierte el dto a entity y se guarde en una variable de ese tipo
        Course courseToSave = courseMapper.toEntity(dto);

        // 4) se le indica al repository que guarde esa variable de tipo entity en la base de datos (save)
        Course savedEntity = courseRepository.save(courseToSave);
        // 5) se convierte la variable de tipo entity, guardada en la base de datos, a dto
        CourseDto saveDto = courseMapper.toDto(savedEntity);

        // 6) se retorna ese dto convertido desde entity
        return saveDto;
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
