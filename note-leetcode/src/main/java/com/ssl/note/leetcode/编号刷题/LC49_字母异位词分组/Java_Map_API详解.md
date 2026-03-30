# Java Map API 详解：从 put 到 compute 的全面解析

## 1. 核心方法对比

| 方法 | 功能 | 参数 | 返回值 | 适用场景 |
|------|------|------|--------|----------|
| `put(K key, V value)` | 插入/覆盖键值对 | key, value | 旧值（若存在）/null（若不存在） | 直接设置键值对，不关心是否已存在 |
| `putIfAbsent(K key, V value)` | 仅当键不存在时插入 | key, value | 旧值（若存在）/新值（若插入） | 避免覆盖已有值，如初始化默认值 |
| `compute(K key, BiFunction<K, V, V> f)` | 对键执行计算（无论是否存在） | key, 函数(键, 当前值)→新值 | 计算后的新值 | 需要基于键和当前值动态计算新值 |
| `computeIfAbsent(K key, Function<K, V> f)` | 键不存在时计算并插入 | key, 函数(键)→新值 | 新值（若插入）/旧值（若存在） | 延迟初始化（如嵌套集合） |
| `computeIfPresent(K key, BiFunction<K, V, V> f)` | 键存在时计算并更新 | key, 函数(键, 当前值)→新值 | 新值（若更新）/null（若函数返回null） | 基于现有值更新（如计数、累加） |

## 2. 详细解析与示例

### 2.1 `put(K key, V value)`

**功能**：直接插入或覆盖键值对，不判断键是否存在。

**返回值**：若键已存在，返回被覆盖的旧值；若不存在，返回 `null`。

**示例**：
```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1); // 返回 null（首次插入）
map.put("a", 2); // 返回 1（覆盖旧值）
```

### 2.2 `putIfAbsent(K key, V value)`

**功能**：仅当键不存在时插入值，若键已存在则不操作。

**返回值**：若键已存在，返回旧值；若不存在，返回新插入的值。

**示例**：
```java
Map<String, Integer> map = new HashMap<>();
map.putIfAbsent("a", 1); // 返回 1（插入成功）
map.putIfAbsent("a", 2); // 返回 1（未覆盖）
```

**适用场景**：初始化默认值（如为不存在的键设置初始计数为0）。

### 2.3 `compute(K key, BiFunction<K, V, V> remappingFunction)`

**功能**：对指定键执行计算，无论键是否存在：
- 若键存在：使用 `(key, 当前值)` 作为参数调用函数，返回新值并更新。
- 若键不存在：使用 `(key, null)` 作为参数调用函数，返回新值并插入。
- 若函数返回 `null`：删除该键（若存在）。

**参数**：`BiFunction` 接收两个参数（键和当前值），返回新值。

**返回值**：计算后的新值（若函数返回非null）或 `null`（若函数返回null）。

**示例**：
```java
Map<String, Integer> map = new HashMap<>();
// 键不存在时，计算新值并插入
map.compute("a", (k, v) -> (v == null) ? 1 : v + 1); // 返回 1（插入）
// 键存在时，基于旧值计算新值
map.compute("a", (k, v) -> (v == null) ? 1 : v + 1); // 返回 2（更新）
// 函数返回null时，删除键
map.compute("a", (k, v) -> null); // 返回 null（删除键）
```

**适用场景**：需要基于键和当前值动态计算新值（如复杂的更新逻辑）。

### 2.4 `computeIfAbsent(K key, Function<K, V> mappingFunction)`

**功能**：仅当键不存在时，执行函数计算新值并插入。

**参数**：`Function` 接收键作为参数，返回新值。

**返回值**：若键不存在，返回计算的新值；若存在，返回旧值。

**示例**：
```java
Map<String, List<String>> map = new HashMap<>();
// 键不存在时，创建新列表并添加元素
map.computeIfAbsent("group1", k -> new ArrayList<>()).add("item1");
// 键存在时，直接获取列表并添加元素
map.computeIfAbsent("group1", k -> new ArrayList<>()).add("item2");
```

**适用场景**：延迟初始化（如嵌套集合，避免空指针异常）。

### 2.5 `computeIfPresent(K key, BiFunction<K, V, V> remappingFunction)`

**功能**：仅当键存在时，执行函数计算新值并更新。

**参数**：`BiFunction` 接收键和当前值作为参数，返回新值。

**返回值**：若键存在，返回计算的新值（若函数返回非null）；若函数返回 `null`，删除该键并返回 `null`；若键不存在，返回 `null`。

**示例**：
```java
Map<String, Integer> map = new HashMap<>();
map.put("count", 1);
// 键存在时，基于旧值累加
map.computeIfPresent("count", (k, v) -> v + 1); // 返回 2（更新）
// 函数返回null时，删除键
map.computeIfPresent("count", (k, v) -> null); // 返回 null（删除键）
// 键不存在时，无操作
map.computeIfPresent("count", (k, v) -> v + 1); // 返回 null（无操作）
```

**适用场景**：基于现有值更新（如计数、累加、状态转换）。

## 3. `compute` 系列方法的关键区别

- **`compute`**：无论键是否存在都执行函数，可处理插入、更新、删除逻辑。
- **`computeIfAbsent`**：仅键不存在时执行，用于延迟初始化。
- **`computeIfPresent`**：仅键存在时执行，用于基于旧值更新。

## 4. 典型使用场景总结

| 场景 | 推荐方法 | 原因 |
|------|----------|------|
| 直接设置/覆盖值 | `put` | 最直接，不关心键是否存在 |
| 避免覆盖已有值 | `putIfAbsent` | 仅当键不存在时插入，适合初始化默认值 |
| 嵌套集合初始化（如 `Map<String, List<String>>`） | `computeIfAbsent` | 延迟创建集合，避免空指针 |
| 基于现有值更新（如计数、累加） | `computeIfPresent` | 仅对存在的键操作，逻辑清晰 |
| 复杂更新逻辑（需同时处理存在/不存在的情况） | `compute` | 统一处理插入、更新、删除逻辑 |

## 5. 记忆技巧

- **`compute`**：**C**alculate（计算）所有情况（存在/不存在）。
- **`computeIfAbsent`**：**A**bsent（不存在）时计算。
- **`computeIfPresent`**：**P**resent（存在）时计算。

## 6. 实战案例：LC49 字母异位词分组

### 题目描述
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

### 解法思路
使用 `HashMap` 存储分组，键为排序后的字符串，值为字母异位词列表。

### 代码实现（使用 `computeIfAbsent`）

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 将字符串排序作为键
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            // 使用 computeIfAbsent 延迟初始化列表
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
```

### 代码分析
- **`computeIfAbsent` 的优势**：当第一次遇到某个排序后的键时，自动创建新的 `ArrayList` 并添加当前字符串；当再次遇到相同键时，直接获取已存在的列表并添加字符串。
- **简洁性**：一行代码完成了检查键是否存在、创建列表（若不存在）、添加元素的操作，避免了手动的 `if-else` 判断。

## 7. 常见错误与注意事项

1. **`compute` 函数返回 null**：会导致键被删除，需谨慎使用。
2. **`computeIfAbsent` 函数开销**：若计算新值的开销较大，应确保键确实不存在时才执行。
3. **并发安全**：`HashMap` 非线程安全，多线程环境下需使用 `ConcurrentHashMap`。
4. **键的不可变性**：作为键的对象应具有不可变性（如 `String`、`Integer`），避免哈希值变化导致查找失败。

## 8. 总结

Java Map 的 `put`、`putIfAbsent`、`compute` 系列方法各有适用场景：
- **`put`**：直接设置值，最简单直接。
- **`putIfAbsent`**：避免覆盖，适合初始化。
- **`computeIfAbsent`**：延迟初始化，适合嵌套集合。
- **`computeIfPresent`**：基于旧值更新，适合计数累加。
- **`compute`**：统一处理所有情况，适合复杂逻辑。

掌握这些方法的区别和使用场景，能让你在处理 Map 操作时更加得心应手，编写更简洁、高效的代码。