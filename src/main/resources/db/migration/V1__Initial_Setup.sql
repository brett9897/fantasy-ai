-- V1__Initial_Setup.sql

CREATE TYPE league_code AS ENUM ('MLB', 'MILB', 'NHL', 'AHL', 'NFL', 'UFL');

CREATE TABLE athlete (
    id UUID PRIMARY KEY,
    external_api_id VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    league league_code NOT NULL,
    current_team VARCHAR(100) NOT NULL,
    positions VARCHAR(255), -- Comma separated positions (e.g., "P,DH" or "QB")
    age INT,
    statistics JSONB DEFAULT '{}', -- Dynamic statistics based on league
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE fantasy_league (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    league league_code NOT NULL,
    scoring_settings JSONB NOT NULL DEFAULT '{}', -- Statistics and weights (e.g., {"HR": 4, "TD": 6})
    roster_settings JSONB NOT NULL DEFAULT '{}',  -- Required number of each position (e.g., {"QB": 1, "SS": 1})
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE fantasy_team (
    id UUID PRIMARY KEY,
    league_id UUID NOT NULL REFERENCES fantasy_league(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    wins INT NOT NULL DEFAULT 0,
    losses INT NOT NULL DEFAULT 0,
    ties INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Mapping table for the many-to-many relationship between teams and athletes
CREATE TABLE fantasy_team_athlete (
    fantasy_team_id UUID NOT NULL REFERENCES fantasy_team(id) ON DELETE CASCADE,
    athlete_id UUID NOT NULL REFERENCES athlete(id) ON DELETE CASCADE,
    PRIMARY KEY (fantasy_team_id, athlete_id)
);

-- Indices for performance
CREATE INDEX idx_athlete_league ON athlete(league);
CREATE INDEX idx_athlete_external_api_id ON athlete(external_api_id);
CREATE INDEX idx_fantasy_team_league_id ON fantasy_team(league_id);
