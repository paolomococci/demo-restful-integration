/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.demo.controller

import local.example.demo.assembler.VineResourceAssembler
import local.example.demo.exception.VineNotFoundException
import local.example.demo.model.Vine
import local.example.demo.repository.VineRepository
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URISyntaxException

@RestController
@RequestMapping(value = ["/api/vines"])
class VineRestController(
        private val vineRepository: VineRepository,
        private val vineResourceAssembler: VineResourceAssembler
) {

    @PostMapping
    @CrossOrigin(origins = ["*"])
    @Throws(URISyntaxException::class)
    internal fun create(@RequestBody vine: Vine): ResponseEntity<Resource<Vine>> {
        val resource = vineResourceAssembler.toResource(vineRepository.save(vine))
        return ResponseEntity.created(URI(resource.id.expand().href)).body(resource)
    }


    @GetMapping(value = ["/{id}"])
    @CrossOrigin(origins = ["*"])
    @Throws(URISyntaxException::class)
    internal fun read(@PathVariable id: Long?): Resource<Vine> {
        val vine = vineRepository.findById(id!!)
                .orElseThrow {
            VineNotFoundException(id)
        }
        return vineResourceAssembler.toResource(vine)
    }

    @GetMapping
    @CrossOrigin(origins = ["*"])
    @Throws(URISyntaxException::class)
    internal fun readAll(): Resources<Resource<Vine>> {
        val vines = vineRepository.findAll()
                .asSequence()
                .map(vineResourceAssembler::toResource).toList()
        return Resources(
                vines,
                linkTo(methodOn(VineRestController::class.java).readAll())
                        .withSelfRel()
        )
    }
}
