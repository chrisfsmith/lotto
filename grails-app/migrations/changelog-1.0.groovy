databaseChangeLog = {

	changeSet(author: "chris (generated)", id: "1334149970656-1") {
		createTable(tableName: "event") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "eventPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "decimal(6,2)") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(250)") {
				constraints(nullable: "false")
			}

			column(name: "lottery_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "max_attendees", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "start_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-2") {
		createTable(tableName: "lottery") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "lotteryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "completed", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "pick_index", type: "integer")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-3") {
		createTable(tableName: "lottery_user") {
			column(name: "lottery_users_id", type: "bigint")

			column(name: "user_id", type: "bigint")

			column(name: "users_idx", type: "integer")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-4") {
		createTable(tableName: "registration") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registrationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "attendee_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "event_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-5") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-6") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-7") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-8") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "chris (generated)", id: "1334149970656-9") {
		createIndex(indexName: "FK5C6729AA08FBE98", tableName: "event") {
			column(name: "lottery_id")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-10") {
		createIndex(indexName: "FKCADF3011A587D1C", tableName: "lottery_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-11") {
		createIndex(indexName: "FKAF83E8B95047F878", tableName: "registration") {
			column(name: "event_id")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-12") {
		createIndex(indexName: "FKAF83E8B9D53641CD", tableName: "registration") {
			column(name: "attendee_id")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-13") {
		createIndex(indexName: "authority_unique_1334149970606", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-14") {
		createIndex(indexName: "username_unique_1334149970612", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-15") {
		createIndex(indexName: "FK143BF46A1A587D1C", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-16") {
		createIndex(indexName: "FK143BF46A752DB93C", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "chris (generated)", id: "1334149970656-17") {
		addForeignKeyConstraint(baseColumnNames: "lottery_id", baseTableName: "event", constraintName: "FK5C6729AA08FBE98", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lottery", referencesUniqueColumn: "false")
	}

	changeSet(author: "chris (generated)", id: "1334149970656-18") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "lottery_user", constraintName: "FKCADF3011A587D1C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "chris (generated)", id: "1334149970656-19") {
		addForeignKeyConstraint(baseColumnNames: "attendee_id", baseTableName: "registration", constraintName: "FKAF83E8B9D53641CD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "chris (generated)", id: "1334149970656-20") {
		addForeignKeyConstraint(baseColumnNames: "event_id", baseTableName: "registration", constraintName: "FKAF83E8B95047F878", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event", referencesUniqueColumn: "false")
	}

	changeSet(author: "chris (generated)", id: "1334149970656-21") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A752DB93C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "chris (generated)", id: "1334149970656-22") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46A1A587D1C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
