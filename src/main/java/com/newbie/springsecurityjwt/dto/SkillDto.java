package com.newbie.springsecurityjwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newbie.springsecurityjwt.model.developer.Skill;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillDto {
    Long id;
    String name;

    public Skill toSkill() {
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        return skill;
    }

    public static SkillDto fromSkill(Skill skill){
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getId());
        skillDto.setName(skill.getName());
        return skillDto;
    }
}
