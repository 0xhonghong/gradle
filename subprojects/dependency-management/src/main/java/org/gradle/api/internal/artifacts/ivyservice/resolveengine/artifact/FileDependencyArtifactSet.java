/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.artifacts.ivyservice.resolveengine.artifact;

import org.gradle.api.artifacts.component.ComponentIdentifier;
import org.gradle.api.internal.artifacts.transform.ArtifactVariantSelector;
import org.gradle.api.internal.artifacts.type.ArtifactTypeRegistry;
import org.gradle.api.specs.Spec;
import org.gradle.internal.component.local.model.LocalFileDependencyMetadata;
import org.gradle.internal.model.CalculatedValueContainerFactory;

public class FileDependencyArtifactSet implements ArtifactSet {
    private final LocalFileDependencyMetadata fileDependency;
    private final ArtifactTypeRegistry artifactTypeRegistry;
    private final CalculatedValueContainerFactory calculatedValueContainerFactory;

    public FileDependencyArtifactSet(LocalFileDependencyMetadata fileDependency, ArtifactTypeRegistry artifactTypeRegistry, CalculatedValueContainerFactory calculatedValueContainerFactory) {
        this.fileDependency = fileDependency;
        this.artifactTypeRegistry = artifactTypeRegistry;
        this.calculatedValueContainerFactory = calculatedValueContainerFactory;
    }

    @Override
    public ResolvedArtifactSet select(Spec<? super ComponentIdentifier> componentFilter, ArtifactVariantSelector variantSelector, boolean selectFromAllVariants) {
        // Select the artifacts later, as this is a function of the file names and these may not be known yet because the producing tasks have not yet executed
        return new LocalFileDependencyBackedArtifactSet(fileDependency, componentFilter, variantSelector, artifactTypeRegistry, calculatedValueContainerFactory);
    }

}
