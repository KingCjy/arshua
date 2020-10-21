package io.github.kingcjy.bean.util

import io.github.kingcjy.annotations.PrimaryConstructor
import io.github.kingcjy.bean.DefaultBeanFactory
import io.github.kingcjy.bean.definition.ClassBeanDefinition
import io.github.kingcjy.bean.initializer.ClassBeanInitializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by KingCjy on 2020/10/20
 * Github: https://github.com/KingCjy
 */
internal class BeanFactoryUtilsKtTest {

    @Test
    fun findPrimaryConstructorTest() {
        val constructor = findPrimaryConstructor(TestComponent::class)

        assertThat(TestComponent::class.constructors.contains(constructor)).isTrue
        assertThat(TestComponent::class.constructors.find { it.parameters.size == 1 }).isEqualTo(constructor)
    }

    @Test
    fun findParametersTest() {
        val beanFactory = DefaultBeanFactory(ClassBeanInitializer())
        beanFactory.registerBeanDefinition(ClassBeanDefinition(TestComponent::class))
        beanFactory.registerBeanDefinition(ClassBeanDefinition(TestComponent2::class))
        val constructor = findPrimaryConstructor(TestComponent::class)

        val parameters = findParameters(beanFactory, constructor)

        assertThat(parameters.size).isEqualTo(1)
        assertThat(parameters[0]::class).isEqualTo(TestComponent2::class)
    }

    @Test
    fun getMethodParametersTest() {
        val constructor = findPrimaryConstructor(TestComponent::class)
        val methodParameters = getMethodParameters(constructor)

        assertThat(methodParameters.size).isEqualTo(1)
        assertThat(methodParameters[0].type).isEqualTo(TestComponent2::class)
    }

    data class TestComponent(
        val id: Int,
        val name: String
    ) {
        @PrimaryConstructor
        constructor(testComponent2: TestComponent2) : this(testComponent2.id, "없음")
    }

    class TestComponent2 {
        val id = 123
    }
}