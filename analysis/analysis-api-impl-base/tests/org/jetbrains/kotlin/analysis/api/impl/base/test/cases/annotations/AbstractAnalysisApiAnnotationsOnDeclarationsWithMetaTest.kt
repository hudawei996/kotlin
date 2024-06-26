/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.impl.base.test.cases.annotations

import org.jetbrains.kotlin.analysis.api.KtAnalysisSession
import org.jetbrains.kotlin.analysis.api.annotations.KtAnnotationsList

abstract class AbstractAnalysisApiAnnotationsOnDeclarationsWithMetaTest : AbstractAnalysisApiAnnotationsOnDeclarationsTest() {
    override fun renderAnnotations(analysisSession: KtAnalysisSession, annotations: KtAnnotationsList): String {
        return TestAnnotationRenderer.renderAnnotationsWithMeta(analysisSession, annotations)
    }
}
