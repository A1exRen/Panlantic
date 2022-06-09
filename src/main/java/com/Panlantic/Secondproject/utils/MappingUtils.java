package com.Panlantic.Secondproject.utils;

import com.Panlantic.Secondproject.entity.Task;
import com.Panlantic.Secondproject.dto.TaskDto;


public class MappingUtils {
    //из dto в entity
        public static Task mapToEntity(TaskDto dto){
            Task entity = new Task();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setAutor(dto.getAutor());
            return entity;
        }
    }

