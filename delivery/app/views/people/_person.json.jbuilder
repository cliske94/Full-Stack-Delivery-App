json.extract! person, :id, :mailing_address, :phone_number, :first_name, :last_name, :email, :password, :created_at, :updated_at
json.url person_url(person, format: :json)
