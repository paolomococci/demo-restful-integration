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
@CrossOrigin
@RequestMapping(value = ["/api/vines"])
class VineRestController(
        private val vineRepository: VineRepository,
        private val vineResourceAssembler: VineResourceAssembler
) {

    @PostMapping
    @Throws(URISyntaxException::class)
    internal fun create(@RequestBody vine: Vine): ResponseEntity<Resource<Vine>> {
        val resource = vineResourceAssembler.toResource(vineRepository.save(vine))
        return ResponseEntity.created(URI(resource.id.expand().href)).body(resource)
    }


    @GetMapping(value = ["/{id}"])
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

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    internal fun update(@RequestBody update: Vine, @PathVariable id: Long?): ResponseEntity<*> {
        val updated = vineRepository.findById(id!!).map {
            temp ->
            temp.name = update.name
            temp.color = update.color
            vineRepository.save(temp)
        }.orElseGet { vineRepository.save(update) }
        val resource = vineResourceAssembler.toResource(updated)
        return ResponseEntity.created(URI(resource.id.expand().href)).body(resource)
    }

    @PatchMapping("/{id}")
    @Throws(URISyntaxException::class)
    internal fun partialUpdate(@RequestBody update: Vine, @PathVariable id: Long?): ResponseEntity<*> {
        val updated = vineRepository.findById(id!!).map {
            temp ->
            if (!update.name.isNullOrBlank()) temp.name = update.name
            if (!update.color.isNullOrBlank()) temp.color = update.color
            vineRepository.save(temp)
        }.orElseGet { vineRepository.save(update) }
        val resource = vineResourceAssembler.toResource(updated)
        return ResponseEntity.created(URI(resource.id.expand().href)).body(resource)
    }

    @DeleteMapping("/{id}")
    @Throws(URISyntaxException::class)
    internal fun delete(@PathVariable id: Long?): ResponseEntity<*> {
        if (id != null) vineRepository.deleteById(id)
        return ResponseEntity.noContent().build<Any>()
    }

    @GetMapping("/name/{name}")
    @Throws(URISyntaxException::class)
    internal fun searchByName(@PathVariable name: String?): Resources<Resource<Vine>> {
        val vines = vineRepository.findByName(name!!)
                .asSequence()
                .map(vineResourceAssembler::toResource)
                .toList()
        return Resources(
                vines,
                linkTo(methodOn(VineRestController::class.java).searchByName(name)).withSelfRel()
        )
    }

    @GetMapping("/color/{color}")
    @Throws(URISyntaxException::class)
    internal fun searchByColor(@PathVariable color: String?): Resources<Resource<Vine>> {
        val vines = vineRepository.findByColor(color!!)
                .asSequence()
                .map(vineResourceAssembler::toResource)
                .toList()
        return Resources(
                vines,
                linkTo(methodOn(VineRestController::class.java).searchByColor(color)).withSelfRel()
        )
    }
}
