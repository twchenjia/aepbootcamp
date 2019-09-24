# OO Principle

## 开发环境
 - JDK1.8+
 - JUnit 5

## SOLID
- S：Single Responsibility Principle
- O：Open/Close Principle
- L：Liskov Substitution Principle
- I：Interface Segregation Principle
- D：Dependency Inversion Principle


## 参考资料
### Junit5

**参数化测试**:

参数化测试使使用不同参数多次运行测试成为可能。它们与常规的@Test方法一样被声明，但是使用 `@ParameterizedTest`注释。
此外，您必须声明至少一个源，该源将为每个调用提供参数，然后使用测试方法中的参数。

- `@ValueSource` 指定一个文本值数组，并且只能用于为每个参数化测试调用提供一个参数。支持`String` `int`等常用类型

```
@ParameterizedTest
@ValueSource(ints = { 1, 2, 3 })
void testWithValueSource(int argument) {
    assertTrue(argument > 0 && argument < 4);
}
```

- `@CsvSource` 将参数列表表示为逗号分隔的值

```
@ParameterizedTest
@CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
void testWithCsvSource(String first, int second) {
    assertNotNull(first);
    assertNotEquals(0, second);
}
```
- `@MethodSource` 引用测试类或外部类的一个或多个工厂方法,此类工厂方法必须返回流、可迭代、迭代器或参数数组。
此外，这种工厂方法不能接受任何参数。测试类中的工厂方法必须是静态的，除非用@TestInstance(Lifecycle.PER_CLASS)注释测试类;
然而，外部类中的工厂方法必须始终是静态的

```
@ParameterizedTest
@MethodSource("stringProvider")
void testWithSimpleMethodSource(String argument) {
    assertNotNull(argument);
}

static Stream<String> stringProvider() {
    return Stream.of("foo", "bar");
}
```

```
@ParameterizedTest
@MethodSource("stringIntAndListProvider")
void testWithMultiArgMethodSource(String str, int num, List<String> list) {
    assertEquals(3, str.length());
    assertTrue(num >=1 && num <=2);
    assertEquals(2, list.size());
}

static Stream<Arguments> stringIntAndListProvider() {
    return Stream.of(
        Arguments.of("foo", 1, Arrays.asList("a", "b")),
        Arguments.of("bar", 2, Arrays.asList("x", "y"))
    );
}
```
