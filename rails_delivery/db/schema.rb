# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# This file is the source Rails uses to define your schema when running `rails
# db:schema:load`. When creating a new database, `rails db:schema:load` tends to
# be faster and is potentially less error prone than running all of your
# migrations from scratch. Old migrations may fail to apply correctly if those
# migrations use external dependencies or application code.
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 0) do

  create_table "businesses", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.time "close_time"
    t.time "open_time"
    t.string "phone_number", limit: 5
    t.string "url", limit: 45
    t.string "name", limit: 45, null: false
    t.string "mailing_address", limit: 45, null: false
    t.integer "customer_rating"
    t.index ["id"], name: "id_UNIQUE", unique: true
  end

  create_table "cars", primary_key: "VIN", id: :string, limit: 17, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.integer "Drivers_id", null: false
    t.string "insurance_verified_YorN", limit: 1, null: false
    t.string "color", limit: 10
    t.string "make", limit: 45
    t.string "model", limit: 45
    t.string "plate_number", limit: 15, null: false
    t.index ["Drivers_id"], name: "fk_Cars_Drivers1"
    t.index ["VIN"], name: "VIN_UNIQUE", unique: true
  end

  create_table "drivers", primary_key: ["driver_id", "Persons_id"], options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.integer "license_number", null: false
    t.integer "rating"
    t.date "date_hired", null: false
    t.integer "driver_id", null: false, auto_increment: true
    t.integer "Persons_id", null: false
    t.index ["Persons_id"], name: "fk_Drivers_Students1_idx"
    t.index ["driver_id"], name: "driver_id_UNIQUE", unique: true
    t.index ["license_number"], name: "license_number_UNIQUE", unique: true
  end

  create_table "faculty", primary_key: "Persons_id", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.string "title", limit: 45
    t.string "highest_degree", limit: 45
    t.string "degree_college", limit: 45
  end

  create_table "locations", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.text "dropoff_description", null: false
    t.string "name", limit: 45, null: false
    t.string "location_address", limit: 45, null: false
    t.text "gps_coordinates", size: :long, collation: "utf8mb4_bin"
    t.index ["id"], name: "id_UNIQUE", unique: true
  end

  create_table "menu_items", primary_key: "item_code", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.string "name", limit: 45, null: false
    t.text "description"
    t.decimal "cost", precision: 10, null: false
    t.index ["item_code"], name: "item_code_UNIQUE", unique: true
  end

  create_table "menus", primary_key: "menu_id", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.integer "Businesses_id", null: false
    t.index ["Businesses_id"], name: "fk_Menus_Businesses1_idx"
    t.index ["menu_id"], name: "menu_id_UNIQUE", unique: true
  end

  create_table "menus_has_menu_items", primary_key: ["Menus_menu_id", "Menu_Items_item_code"], options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.integer "Menus_menu_id", null: false
    t.integer "Menu_Items_item_code", null: false
    t.index ["Menu_Items_item_code"], name: "fk_Menus_has_Menu_Items_Menu_Items1_idx"
    t.index ["Menus_menu_id"], name: "fk_Menus_has_Menu_Items_Menus1_idx"
  end

  create_table "orders", primary_key: "order_id", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.integer "Drivers_driver_id", null: false
    t.integer "Businesses_id", null: false
    t.datetime "timestamp", null: false
    t.float "miles_total"
    t.float "promotional_factor"
    t.float "order_total", null: false
    t.integer "delivery_charge", null: false
    t.integer "delivery_time_minutes"
    t.integer "Locations_id", null: false
    t.index ["Businesses_id"], name: "fk_Orders_Businesses1_idx"
    t.index ["Drivers_driver_id"], name: "fk_Orders_Drivers1_idx"
    t.index ["Locations_id"], name: "fk_Orders_Locations1_idx"
    t.index ["order_id"], name: "order_id_UNIQUE", unique: true
  end

  create_table "payments", primary_key: "payment_id", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.decimal "amount", precision: 10, null: false
    t.string "payment_type", limit: 10, null: false
    t.integer "card_number"
    t.integer "exp_date"
    t.integer "cvv"
    t.integer "zipcode"
    t.integer "Persons_id", null: false
    t.index ["Persons_id"], name: "fk_Payments_Persons1_idx"
    t.index ["payment_id"], name: "payment_id_UNIQUE", unique: true
  end

  create_table "payroll", primary_key: "transaction_id", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.decimal "gross_pay", precision: 10, null: false
    t.float "hours_worked", null: false
    t.decimal "taxes_taken", precision: 10, null: false
    t.datetime "timestamp", null: false
    t.decimal "YTD_pay", precision: 10, null: false
    t.integer "Drivers_id", null: false
    t.index ["Drivers_id"], name: "fk_Payroll_Drivers1_idx"
    t.index ["transaction_id"], name: "transaction_id_UNIQUE", unique: true
  end

  create_table "persons", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.string "mailing_address", limit: 100
    t.string "phone_number", limit: 15
    t.string "first_name", limit: 45
    t.string "last_name", limit: 45
    t.string "email", limit: 45
    t.string "password", limit: 45
    t.index ["id"], name: "id_UNIQUE", unique: true
  end

  create_table "rating_history", primary_key: "rating_id", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.integer "rating", null: false
    t.datetime "timestamp", null: false
    t.text "description"
    t.integer "Persons_id", null: false
    t.integer "Businesses_id", null: false
    t.integer "Drivers_id", null: false
    t.index ["Businesses_id"], name: "fk_Rating_History_Businesses1_idx"
    t.index ["Drivers_id"], name: "fk_Rating_History_Drivers1_idx"
    t.index ["Persons_id"], name: "fk_Rating_History_Persons1_idx"
    t.index ["rating_id"], name: "rating_id_UNIQUE", unique: true
  end

  create_table "staff", primary_key: "Persons_id", id: :integer, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.string "adminYorN", limit: 1
    t.string "position", limit: 45
    t.index ["Persons_id"], name: "Persons_id_UNIQUE", unique: true
  end

  create_table "students", primary_key: "Persons_id", id: :integer, default: nil, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.integer "student_id"
    t.integer "grad_year"
    t.string "major", limit: 45
    t.string "program_type", limit: 45
  end

  create_table "university", id: false, options: "ENGINE=InnoDB DEFAULT CHARSET=utf8", force: :cascade do |t|
    t.string "url", limit: 45, null: false
    t.string "univ_name", limit: 100, null: false
    t.string "address", limit: 100, null: false
  end

  add_foreign_key "cars", "drivers", column: "Drivers_id", primary_key: "driver_id", name: "fk_Cars_Drivers1"
  add_foreign_key "drivers", "students", column: "Persons_id", primary_key: "Persons_id", name: "fk_Drivers_Students1"
  add_foreign_key "faculty", "persons", column: "Persons_id", name: "fk_Faculty_Persons1"
  add_foreign_key "menus", "businesses", column: "Businesses_id", name: "fk_Menus_Businesses1"
  add_foreign_key "menus_has_menu_items", "menu_items", column: "Menu_Items_item_code", primary_key: "item_code", name: "fk_Menus_has_Menu_Items_Menu_Items1"
  add_foreign_key "menus_has_menu_items", "menus", column: "Menus_menu_id", primary_key: "menu_id", name: "fk_Menus_has_Menu_Items_Menus1"
  add_foreign_key "orders", "businesses", column: "Businesses_id", name: "fk_Orders_Businesses1"
  add_foreign_key "orders", "drivers", column: "Drivers_driver_id", primary_key: "driver_id", name: "fk_Orders_Drivers1"
  add_foreign_key "orders", "locations", column: "Locations_id", name: "fk_Orders_Locations1"
  add_foreign_key "payments", "persons", column: "Persons_id", name: "fk_Payments_Persons1"
  add_foreign_key "payroll", "drivers", column: "Drivers_id", primary_key: "driver_id", name: "fk_Payroll_Drivers1"
  add_foreign_key "rating_history", "businesses", column: "Businesses_id", name: "fk_Rating_History_Businesses1"
  add_foreign_key "rating_history", "drivers", column: "Drivers_id", primary_key: "driver_id", name: "fk_Rating_History_Drivers1"
  add_foreign_key "rating_history", "persons", column: "Persons_id", name: "fk_Rating_History_Persons1"
  add_foreign_key "staff", "persons", column: "Persons_id", name: "fk_Staff_Persons1"
  add_foreign_key "students", "persons", column: "Persons_id", name: "fk_Students_Persons"
end
