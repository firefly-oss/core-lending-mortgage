# Core Lending Mortgages Microservice

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.java.net/projects/jdk/25/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)

## Overview

The **Core Lending Mortgages Microservice** is a specialized component of the **Firefly OpenCore Banking Platform**, developed by **Firefly Software Solutions Inc** under the Apache 2.0 license. This microservice is responsible for managing **mortgage-specific contractual terms and legal documentation**.

This service provides a focused, cohesive domain model for mortgage agreements, handling only the contractual and legal aspects that are unique to mortgage products. All operational servicing (payments, disbursements, accruals, notifications, rate changes) is delegated to the **core-lending-loan-servicing** microservice.

**Organization**: [firefly-oss](https://github.com/firefly-oss)  
**Website**: [getfirefly.io](https://getfirefly.io)  
**License**: Apache 2.0

## Architecture

### Microservices Separation of Concerns

The Firefly lending platform follows a clear separation of concerns across multiple microservices:

| Microservice | Responsibility |
|--------------|----------------|
| **core-lending-loan-origination** | Loan applications, credit analysis, underwriting, proposed offers |
| **core-lending-mortgages** | **Mortgage-specific contractual terms and legal documentation** |
| **core-lending-loan-servicing** | Operational servicing: payments, disbursements, accruals, notifications, rate changes, installments, balances |
| **core-lending-collateral-management** | Collateral valuation, insurance, property management |

### What This Microservice Does

**Mortgage Agreement Management**
- Mortgage-specific contractual terms
- Legal and regulatory documentation (notary, deed, registry)
- Mortgage product classification (conventional, Islamic, reverse, government-backed)
- Lien position and priority
- Contractual features and permissions (assumability, portability, prepayment terms)

**Interest Rate Contractual Terms**
- Initial interest rate (what was AGREED at contract signing)
- Rate type (fixed, variable, hybrid)
- Reference rate and margin (for variable/hybrid mortgages)
- Rate caps, floors, and periodic caps (contractual limits)

**Islamic Finance Support**
- Murabaha (cost-plus financing)
- Ijara (lease-to-own)
- Musharaka (partnership/joint ownership)
- Diminishing Musharaka

**Global Mortgage Products**
- 40+ mortgage types from around the world
- Government-backed programs (FHA, VA, CMHC, Help to Buy, KfW, INFONAVIT)
- Specialized products (bridge loans, construction, reverse mortgages)
- Commercial and investment property mortgages

### What This Microservice Does NOT Do

**Operational Servicing** (handled by core-lending-loan-servicing)
- Payment schedules and installments
- Actual payment processing
- Disbursements
- Interest accruals
- Rate changes (operational changes to current rate)
- Balance tracking
- Notifications
- Escrow management
- Rebates and commissions

**Loan Origination** (handled by core-lending-loan-origination)
- Loan applications
- Credit analysis
- Underwriting
- Proposed offers

**Collateral Management** (handled by core-lending-collateral-management)
- Property valuation
- Insurance management
- Collateral tracking

## Technology Stack

- **Java 25**: Latest LTS version with virtual threads support
- **Spring Boot 3.x**: Modern Spring framework with native compilation support
- **Spring WebFlux**: Reactive web framework for non-blocking I/O
- **R2DBC**: Reactive database connectivity for PostgreSQL
- **PostgreSQL**: Primary database with advanced features
- **Flyway**: Database migration and versioning
- **Maven**: Build automation and dependency management
- **OpenAPI 3**: API documentation and client generation
- **MapStruct**: Type-safe bean mapping
- **Lombok**: Boilerplate code reduction
- **Docker**: Containerization and deployment

## Data Model

The microservice manages a single, focused entity:

### MortgageAgreement

The core entity representing mortgage-specific contractual terms.

**Key Fields:**

**External References:**
- `applicationId`: Reference to LoanApplication (Loan Origination)
- `servicingCaseId`: Reference to LoanServicingCase (Loan Servicing)
- `proposedOfferId`: Reference to ProposedOffer (Loan Origination)

**Mortgage-Specific Terms:**
- `agreementStatus`: DRAFT, ACTIVE, SUSPENDED, CLOSED, CANCELLED
- `mortgageType`: Type of mortgage product (40+ global types)
- `lienPosition`: FIRST, SECOND, THIRD, SUBORDINATE

**Interest Rate Contractual Terms:**
- `rateType`: FIXED, VARIABLE, HYBRID
- `initialInterestRate`: Initial rate at contract start
- `referenceRate`: Reference rate index (EURIBOR, LIBOR, SOFR, PRIME)
- `marginRate`: Margin/spread added to reference rate
- `fixedRatePeriodMonths`: Initial fixed period for hybrid mortgages
- `rateCap`: Maximum interest rate cap (contractual limit)
- `rateFloor`: Minimum interest rate floor (contractual limit)
- `periodicRateCap`: Maximum rate change per adjustment period

**Islamic Finance Fields:**
- `profitRate`: Profit rate for Islamic mortgages
- `rentalRate`: Rental rate for Ijara mortgages
- `bankOwnershipPercentage`: For Musharaka/diminishing Musharaka
- `purchasePrice`: Purchase price for Murabaha
- `markupAmount`: Markup for Murabaha

**Legal and Regulatory:**
- `notaryName`, `notaryRegistrationNumber`: Notary information
- `deedNumber`, `deedDate`: Public deed information
- `registryOffice`, `registryVolume`, `registryBook`, `registryFolio`: Property registry information
- `registryInscriptionDate`: Date registered in property registry

**Contractual Features:**
- `isAssumable`: Can mortgage be assumed by new buyer
- `isPortable`: Can be transferred to new property
- `isRecourse`: Recourse vs non-recourse loan
- `allowsEarlyRepayment`: Early repayment allowed per contract
- `earlyRepaymentPenaltyRate`: Contractual penalty % for early repayment
- `earlyRepaymentPenaltyPeriodMonths`: Period during which penalty applies
- `allowsPartialPrepayment`: Partial prepayments allowed per contract
- `partialPrepaymentMinAmount`: Minimum amount for partial prepayment
- `partialPrepaymentMaxPerYear`: Maximum prepayment per year
- `subrogationAllowed`: Can be transferred to another lender
- `subrogationFee`: Contractual fee for subrogation

**Audit Fields:**
- `agreementSignedDate`: Date agreement was signed
- `agreementEffectiveDate`: Date agreement becomes effective
- `createdBy`, `createdAt`, `updatedBy`, `updatedAt`: Audit trail

## Enumerations

### AgreementStatusEnum

Agreement lifecycle status:
- **DRAFT**: Agreement is being drafted
- **ACTIVE**: Agreement is active
- **SUSPENDED**: Agreement is temporarily suspended
- **CLOSED**: Agreement is closed/completed
- **CANCELLED**: Agreement was cancelled

### MortgageTypeEnum

Mortgage product types (39 values):

**Standard:**
- `CONVENTIONAL` - Standard mortgage
- `GOVERNMENT_BACKED` - FHA, VA, USDA (US), Help to Buy (UK), etc.
- `JUMBO` - Above conforming loan limits

**Reverse/Equity:**
- `REVERSE` - Reverse mortgage (equity release)
- `EQUITY_RELEASE` - Lifetime mortgage (UK/Europe)
- `HOME_REVERSION` - Home reversion plan

**Islamic Finance:**
- `ISLAMIC_MURABAHA` - Cost-plus financing
- `ISLAMIC_IJARA` - Lease-to-own
- `ISLAMIC_MUSHARAKA` - Partnership/equity participation
- `ISLAMIC_DIMINISHING_MUSHARAKA` - Diminishing partnership

**Specialized:**
- `BRIDGE` - Short-term bridge loan
- `CONSTRUCTION` - Construction-to-permanent
- `RENOVATION` - Purchase + renovation financing
- `SHARED_EQUITY` - Shared ownership schemes
- `SHARED_APPRECIATION` - Lender shares in property appreciation
- `BALLOON` - Large final payment
- `INTEREST_ONLY` - Interest-only mortgage
- `OFFSET` - Offset mortgage (savings offset interest)
- `FLEXIBLE` - Flexible payment mortgage
- `TRACKER` - Tracks base rate
- `DISCOUNT` - Discount off standard variable rate
- `CAPPED` - Interest rate cap
- `PORTABLE` - Portable mortgage
- `ASSUMABLE` - Assumable by buyer
- `NON_RECOURSE` - Lender can't pursue borrower beyond collateral
- `RECOURSE` - Full recourse to borrower

**Commercial/Investment:**
- `COMMERCIAL` - Commercial property mortgage
- `BUY_TO_LET` - Investment/rental property
- `SECOND_HOME` - Vacation/second home

**Other:**
- `SELF_BUILD` - Self-build mortgage
- `GUARANTOR` - Guarantor mortgage
- `JOINT_BORROWER_SOLE_PROPRIETOR` - JBSP mortgage
- `EQUITY_LOAN` - Government equity loan
- `RIGHT_TO_BUY` - Right to buy scheme
- `HELP_TO_BUY` - Help to buy scheme
- `LIFETIME` - Lifetime mortgage
- `AGRICULTURAL` - Agricultural/farm mortgage
- `LAND` - Land-only mortgage
- `OTHER` - Other types

### LienPositionEnum

Legal priority position:
- **FIRST**: First lien/mortgage
- **SECOND**: Second lien/mortgage
- **THIRD**: Third lien/mortgage
- **SUBORDINATE**: Subordinate lien

### RateTypeEnum

Interest rate structure:
- **FIXED**: Fixed interest rate for entire term
- **VARIABLE**: Variable/adjustable rate
- **HYBRID**: Hybrid (fixed period then variable)

## Module Structure

The microservice follows a clean, modular architecture:

- **`core-lending-mortgages-interfaces`**: DTOs, interfaces, enums, and API contracts
- **`core-lending-mortgages-models`**: Entities, repositories, and database migrations
- **`core-lending-mortgages-core`**: Business logic, service implementations, and mappers
- **`core-lending-mortgages-web`**: REST controllers, web configuration, and application entry point
- **`core-lending-mortgages-sdk`**: Generated client SDK for external integrations

## Prerequisites

- **Java Development Kit (JDK) 21** or higher
- **Maven 3.8+** for build management
- **PostgreSQL 13+** for database
- **Docker** (optional, for containerized deployment)
- **Git** for version control

## Setup and Installation

### Local Development

1. **Clone the repository:**
   ```bash
   git clone git@github.com:firefly-oss/core-lending-mortgage.git
   cd core-lending-mortgage
   ```

2. **Set up environment variables:**
   ```bash
   export DB_HOST=localhost
   export DB_PORT=5432
   export DB_NAME=mortgage
   export DB_USERNAME=your_username
   export DB_PASSWORD=your_password
   export DB_SSL_MODE=disable
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run -pl core-lending-mortgages-web
   ```

5. **Access the application:**
   - Application: http://localhost:8080
   - API Documentation: http://localhost:8080/swagger-ui.html
   - Health Check: http://localhost:8080/actuator/health

## API Documentation

The microservice provides REST APIs documented with OpenAPI 3.0:

- **Local Environment**: http://localhost:8080/swagger-ui.html

### API Endpoints

| Resource | Base Path | Description |
|----------|-----------|-------------|
| Mortgage Agreements | `/api/v1/mortgage-agreements` | Mortgage agreement management |

## Development Guidelines

### Coding Standards

- **Java 25 Features**: Utilize modern Java features
- **Reactive Programming**: Use Project Reactor for non-blocking operations
- **Validation**: All DTOs include comprehensive Jakarta validation annotations
- **Documentation**: Document all public APIs with OpenAPI 3.0 annotations
- **Testing**: Maintain high test coverage
- **Code Quality**: Follow Google Java Style Guide

### Database Guidelines

- **Migrations**: All schema changes must be versioned using Flyway migrations
- **UUIDs**: Use UUID primary keys for all entities
- **Enums**: Database enums are mapped to Java enums with automatic casting
- **Auditing**: All entities include audit timestamps

## Testing

```bash
# Run all tests
mvn clean test

# Run tests for specific module
mvn test -pl core-lending-mortgages-core

# Run integration tests
mvn verify

# Run tests with coverage
mvn clean test jacoco:report
```

## License

This project is licensed under the **Apache License 2.0** - see the [LICENSE](LICENSE) file for details.

```
Copyright 2025 Firefly Software Solutions Inc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Support and Contact

- **Website**: [getfirefly.io](https://getfirefly.io)
- **GitHub Organization**: [firefly-oss](https://github.com/firefly-oss)
- **Documentation**: [docs.getfirefly.io](https://docs.getfirefly.io)
- **Community**: [community.getfirefly.io](https://community.getfirefly.io)

---

**Firefly OpenCore Banking Platform** - Building the future of open banking infrastructure.

