# Checker Framework Compile-Time Security Extension

A pluggable Java compiler (`javac`) static analysis extension built on the **Checker Framework (`3.42.0`)** that enforces compile-time taint tracking to prevent injection vulnerabilities before bytecode generation.

## Security Architecture & Coverage

The extension introduces three independent, fail-closed subtyping lattices ($\text{Untainted} <: \text{Tainted}$) using `@DefaultQualifierInHierarchy` so all unannotated variables default to untrusted (`@Tainted*`) unless explicitly validated or derived from safe literals.

| Vulnerability Class | CWE / OWASP Mapping | Type Hierarchy | Protected Sink Stubs (`jdk.astub`) |
| :--- | :--- | :--- | :--- |
| **SQL Injection** | CWE-89 / OWASP A03 | `@Untainted <: @Tainted` | `java.sql.Statement` (`executeQuery`, `executeUpdate`), `java.sql.Connection` (`prepareStatement`) |
| **Path Traversal** | CWE-22 / OWASP A01 | `@UntaintedPath <: @TaintedPath` | `java.io.File` constructors, `java.nio.file.Paths.get` |
| **Command Injection** | CWE-78 / OWASP A03 | `@UntaintedCommand <: @TaintedCommand` | `java.lang.Runtime.exec`, `java.lang.ProcessBuilder` |

### Key Components
* **Custom Type Checkers (`src/main/java/.../*Checker.java`):** Subclass `BaseTypeChecker` to hook custom taint processors directly into `javac`.
* **Fail-Closed Qualifiers (`src/main/java/.../qual/*.java`):** Define `@SubtypeOf` lattices with `@DefaultQualifierInHierarchy` on the top (`Tainted`) qualifiers.
* **Literal Propagation (`SqlInjectionAnnotatedTypeFactory.java`):** Overrides `TreeAnnotator` so compile-time string literals default to `@Untainted`, preserving type safety across valid string concatenations while rejecting tainted concatenations.
* **Declarative Sink Overlays (`src/main/resources/.../jdk.astub`):** Enforce `@Untainted*` parameter contracts on standard JDK APIs without modifying runtime libraries.
* **Sanitization Boundary (`SqlSanitizer.java`):** Explicit trust-boundary function refining `@Tainted String` inputs into `@Untainted String` outputs.

---

## Repository Structure

```text
├── .github/workflows/ci.yml              # Multi-OS CI, OWASP SCA & Gitleaks pipeline
├── pom.xml                               # Maven build, Checker Framework 3.42.0 & OWASP Dependency-Check
├── src/
│   ├── main/java/org/checkerframework/checker/
│   │   ├── sqli/                         # SQL Injection checker, factory, qualifiers & sanitizer
│   │   ├── pathtraversal/                # Path Traversal checker & qualifiers
│   │   └── commandinjection/             # Command Injection checker & qualifiers
│   ├── main/resources/org/checkerframework/checker/
│   │   └── {sqli,pathtraversal,commandinjection}/jdk.astub
│   └── test/java/org/checkerframework/checker/
│       └── {sqli,pathtraversal,commandinjection}/*Test.java
└── tests/
    └── {sqli,pathtraversal,commandinjection}/*TestCases.java
mvn clean test --batch-mode
mvn dependency:tree

# Linux / macOS
export NVD_API_KEY="your-nvd-api-key"
mvn org.owasp:dependency-check-maven:check --batch-mode

# Windows PowerShell
$env:NVD_API_KEY="your-nvd-api-key"
mvn org.owasp:dependency-check-maven:check --batch-mode
License
This project is licensed under the MIT License — see the LICENSE file for details.