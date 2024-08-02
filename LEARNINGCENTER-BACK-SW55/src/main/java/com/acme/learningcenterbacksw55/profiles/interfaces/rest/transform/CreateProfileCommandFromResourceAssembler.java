package com.acme.learningcenterbacksw55.profiles.interfaces.rest.transform;

import com.acme.learningcenterbacksw55.profiles.domain.model.commands.CreateProfileCommand;
import com.acme.learningcenterbacksw55.profiles.interfaces.rest.resources.CreateProfileResource;

/**
 * CreateProfilecommandFromResourceAssembler
 *
 * <p>
 *     Assembler to create a {@link CreateProfileCommand} from a {@link CreateProfileResource}
 *     <br>
 *     This class applies the assembler pattern to transform a CreateProfile Resource resource
 *     into a CreateProfileCommand command
 *     </br>*
 * </p>
 */
public class CreateProfileCommandFromResourceAssembler {


    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(), resource.lastName(), resource.email(),
                resource.street(), resource.number(), resource.city(), resource.state(), resource.zipCode(), resource.country());
    }
}
